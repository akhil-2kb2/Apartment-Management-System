'use client';

import { useState } from 'react';
import { useAuthContext } from '@/context/AuthContext';

export default function LoginForm() {
  const { loginUser } = useAuthContext();
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');

  const submit = async (e: React.FormEvent) => {
    e.preventDefault();
    try {
      await loginUser({ username, password });
    } catch {
      alert('Login failed');
    }
  };

  return (
    <div className="min-h-screen flex justify-center items-center">
      <form
        onSubmit={submit}
        className="bg-white p-6 rounded shadow w-80 space-y-4"
      >
        <h2 className="text-xl font-semibold text-center">Sign in</h2>

        <input
          type="text"
          placeholder="Username"
          value={username}
          onChange={e => setUsername(e.target.value)}
          className="w-full border p-2 rounded-full mt-4"
          required
        />

        <input
          type="password"
          placeholder="Password"
          value={password}
          onChange={e => setPassword(e.target.value)}
          className="w-full border p-2 mt-2 rounded-full mt-6"
          required
        />

        <button
          type="submit"
          className="w-full bg-blue-600 text-white py-2 rounded-full"
        >
          Login
        </button>
      </form>
    </div>
  );
}
