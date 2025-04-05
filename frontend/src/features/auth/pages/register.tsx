// src/features/auth/pages/register.tsx
'use client';

import { useState } from 'react';
import { register as registerUser } from '../services/auth';
import { useAuth } from '@/context/AuthContext';
import { useRouter } from 'next/navigation';

export default function RegisterPage() {
  const [form, setForm] = useState({ name: '', email: '', password: '', role: 'TENANT' });
  const [error, setError] = useState('');
  const { login } = useAuth();
  const router = useRouter();

  const handleChange = (e: React.ChangeEvent<HTMLInputElement | HTMLSelectElement>) => {
    setForm({ ...form, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    try {
      const { user, token } = await registerUser(form);
      login(user, token);
      router.push('/features/dashboard');
    } catch (err) {
      setError('Registration failed');
    }
  };

  return (
    <form onSubmit={handleSubmit} className="max-w-md mx-auto mt-20 space-y-4 p-4 border rounded">
      <h1 className="text-2xl font-bold">Register</h1>
      {error && <p className="text-red-500">{error}</p>}

      <input name="name" className="w-full border p-2" type="text" placeholder="Name" value={form.name} onChange={handleChange} />
      <input name="email" className="w-full border p-2" type="email" placeholder="Email" value={form.email} onChange={handleChange} />
      <input name="password" className="w-full border p-2" type="password" placeholder="Password" value={form.password} onChange={handleChange} />
      <select name="role" className="w-full border p-2" value={form.role} onChange={handleChange}>
        <option value="TENANT">Tenant</option>
        <option value="OWNER">Owner</option>
        <option value="ADMIN">Admin</option>
        {/* You can add more roles as per your backend enum */}
      </select>

      <button className="bg-green-600 text-white px-4 py-2 rounded hover:bg-green-700" type="submit">Register</button>
    </form>
  );
}
