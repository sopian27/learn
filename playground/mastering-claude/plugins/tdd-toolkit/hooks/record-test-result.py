import json
import os
import re
import sys
from datetime import datetime, timezone

TEST_COMMAND_RE = re.compile(
    r"\b(mvn(\s+\S+)*\s+test|pytest|npm\s+(run\s+)?test|gradle(w)?\s+.*test|go\s+test|jest)\b",
    re.I,
)
MVN_SUMMARY_RE = re.compile(r"Tests run:\s*(\d+),\s*Failures:\s*(\d+),\s*Errors:\s*(\d+)")
PYTEST_FAIL_RE = re.compile(r"\d+\s+failed")
PYTEST_PASS_RE = re.compile(r"\d+\s+passed")


def classify(output: str):
    m = MVN_SUMMARY_RE.search(output)
    if m:
        failures, errors = int(m.group(2)), int(m.group(3))
        return "red" if (failures + errors) > 0 else "green"
    if PYTEST_FAIL_RE.search(output):
        return "red"
    if PYTEST_PASS_RE.search(output):
        return "green"
    if re.search(r"\b(failing|FAILED|BUILD FAILURE)\b", output):
        return "red"
    if re.search(r"\b(passed|BUILD SUCCESS|ok)\b", output, re.I):
        return "green"
    return None


def main():
    payload = json.load(sys.stdin)
    command = payload.get("tool_input", {}).get("command", "")
    if not TEST_COMMAND_RE.search(command):
        return

    response = payload.get("tool_response", {})
    if isinstance(response, dict):
        output = str(response.get("stdout", "")) + "\n" + str(response.get("stderr", ""))
    else:
        output = str(response)

    status = classify(output)
    if status is None:
        return  # ambiguous output, don't overwrite existing state with a guess

    state_dir = os.path.join(os.getcwd(), ".claude")
    os.makedirs(state_dir, exist_ok=True)
    state_path = os.path.join(state_dir, "tdd-state.json")
    with open(state_path, "w", encoding="utf-8") as f:
        json.dump(
            {
                "status": status,
                "command": command,
                "timestamp": datetime.now(timezone.utc).isoformat(),
            },
            f,
        )


if __name__ == "__main__":
    main()
