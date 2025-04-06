// src/shared/hooks/useAuth.ts
import { useState, useEffect } from 'react';
import { login, getProfile } from '@/features/auth/services/auth';
import { AuthDTO, User } from '@/types/auth';

export function useAuth() {
  const [user, setUser] = useState<User | null>(null);
  const [token, setToken] = useState<string | null>(null);

  useEffect(() => {
    const saved = localStorage.getItem('token');
    if (saved) {
      setToken(saved);
      getProfile(saved).then(setUser).catch(() => logout());
    }
  }, []);

  const loginUser = async (dto: AuthDTO) => {
    const { token } = await login(dto);
    localStorage.setItem('token', token);
    setToken(token);
    const profile = await getProfile(token);
    setUser(profile);
  };

  const logout = () => {
    localStorage.removeItem('token');
    setUser(null);
    setToken(null);
  };

  return { user, token, loginUser, logout };
}
