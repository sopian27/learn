import json
import os
import sys

CODE_EXTENSIONS = {".java", ".py", ".js", ".ts", ".jsx", ".tsx", ".go", ".rb"}


def is_test_file(path: str) -> bool:
    name = os.path.basename(path).lower()
    return "test" in name or "spec" in name


def main():
    payload = json.load(sys.stdin)
    file_path = payload.get("tool_input", {}).get("file_path", "")
    if not file_path:
        return

    _, ext = os.path.splitext(file_path)
    if ext not in CODE_EXTENSIONS or is_test_file(file_path):
        return  # not a guarded implementation file

    state_path = os.path.join(os.getcwd(), ".claude", "tdd-state.json")
    if not os.path.exists(state_path):
        print(
            "TDD gate (tdd-toolkit): no test run observed yet this session. "
            "Run the relevant test suite once (ideally a failing/RED test) before editing "
            f"{file_path}.",
            file=sys.stderr,
        )
        sys.exit(2)


if __name__ == "__main__":
    main()
