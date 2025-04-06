// src/types/auth.ts
export type Shift = 'MORNING' | 'EVENING' | 'NIGHT';

export interface AuthDTO {
  username: string;
  password: string;
  roles?: string[];
  isActive?: boolean;
  shift?: Shift;
}

export interface User {
  username: string;
  roles: string[];
}
