// Belum dijalankan di sini (k6 tidak terpasang). Jalankan: k6 run orders.js
import http from 'k6/http';
import { check, sleep } from 'k6';

export const options = {
  stages: [
    { duration: '30s', target: 50 },  // naik pelan
    { duration: '1m',  target: 50 },  // tahan
    { duration: '10s', target: 0 },
  ],
  thresholds: {
    http_req_duration: ['p(95)<500'], // gagal kalau p95 di atas 500 ms
    http_req_failed:   ['rate<0.01'], // gagal kalau error di atas 1%
  },
};

export default function () {
  const res = http.get('http://localhost:8080/orders?size=20');
  check(res, { 'status 200': (r) => r.status === 200 });
  sleep(1); // jeda seperti user asli
}
