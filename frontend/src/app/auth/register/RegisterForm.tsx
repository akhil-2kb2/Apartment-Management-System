'use client';

import { useState } from 'react';
import { useRouter } from 'next/navigation';
import type { Shift } from '@/types/auth';

interface RegisterFormProps {
  role: 'admin' | 'secretary' | 'security';
}

export default function RegisterForm({ role }: RegisterFormProps) {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [shift, setShift] = useState<Shift>('MORNING');
  const router = useRouter();   

  const submit = async (e: React.FormEvent) => {
    e.preventDefault();

    const payload: {
      username: string;
      password: string;
      shift?: Shift;
    } = {
      username,
      password,
    };

    if (role === 'security') {
      payload.shift = shift;
    }

    const endpoint = `/auth/register-${role}`;

    try {
      const res = await fetch(`${process.env.NEXT_PUBLIC_API_URL}${endpoint}`, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(payload),
      });

      if (!res.ok) {
        const data = await res.json();
        alert(data.error || 'Registration failed');
        return;
      }

      alert('Registration successful!');
      router.push('/auth/login');
    } catch (error) {
      console.error('Registration error:', error);
      alert('Server error');
    }
  };

  return (
    <div className="min-h-screen flex justify-center items-center">
      <form onSubmit={submit} className="bg-white p-6 rounded shadow w-96 space-y-4">
        <h2 className="text-xl font-semibold text-center capitalize">Register {role}</h2>

        <input
          type="text"
          placeholder="Username"
          value={username}
          onChange={e => setUsername(e.target.value)}
          className="w-full border p-2"
          required
          aria-label="Username"
        />

        <input
          type="password"
          placeholder="Password"
          value={password}
          onChange={e => setPassword(e.target.value)}
          className="w-full border p-2 mt-2"
          required
          aria-label="Password"
        />

        {role === 'security' && (
          <label className="block w-full">
            <span className="text-sm font-medium text-gray-700">Shift</span>
            <select
              value={shift}
              onChange={e => setShift(e.target.value as Shift)}
              className="w-full border p-2 mt-1"
              required
            >
              <option value="MORNING">Morning</option>
              <option value="EVENING">Evening</option>
              <option value="NIGHT">Night</option>
            </select>
          </label>
        )}

        <button
          type="submit"
          className="w-full bg-green-600 text-white py-2 rounded"
        >
          Register
        </button>
      </form>
    </div>
  );
}
