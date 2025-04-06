// src/features/auth/services/auth.ts
import { AuthDTO, User } from '@/types/auth';

const API_URL = process.env.NEXT_PUBLIC_API_URL || '';

export async function login(data: AuthDTO) {
  const res = await fetch(`${API_URL}/auth/login`, {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify(data),
  });

  if (!res.ok) throw new Error('Invalid credentials');
  return res.json(); // { token }
}

export async function getProfile(token: string): Promise<User> {
  const res = await fetch(`${API_URL}/auth/me`, {
    headers: { Authorization: `Bearer ${token}` },
  });

  if (!res.ok) throw new Error('Not authenticated');
  return res.json();
}
 