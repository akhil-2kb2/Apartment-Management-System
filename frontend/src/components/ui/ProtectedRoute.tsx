// src/components/ProtectedRoute.tsx
'use client';

import { useAuthContext } from '@/context/AuthContext';
import { useRouter } from 'next/navigation';
import { useEffect } from 'react';

export default function ProtectedRoute({ roles, children }: {
  roles?: string[];
  children: React.ReactNode;
}) {
  const { user } = useAuthContext();
  const router = useRouter();

  useEffect(() => {
    if (!user) {
      router.push('/auth/login');
    } else if (roles && !roles.some(r => user.roles.includes(r))) {
      router.push('/unauthorized');
    }
  }, [user, roles]);

  return <>{user && (!roles || roles.some(r => user.roles.includes(r))) ? children : null}</>;
}
