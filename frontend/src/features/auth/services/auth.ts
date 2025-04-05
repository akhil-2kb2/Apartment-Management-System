// src/features/auth/services/auth.ts
import api from '@/services/api';

export const login = async (email: string, password: string) => {
  const res = await api.post('/auth/login', { email, password });
  return res.data;
};

export const register = async (data: { name: string; email: string; password: string; role: string }) => {
  const res = await api.post('/auth/register', data);
  return res.data;
};
