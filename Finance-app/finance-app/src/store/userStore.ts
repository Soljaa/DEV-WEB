import { create } from "zustand";

interface UserStore {
  userToken: string;
  loginTryed: boolean;
  userId: Number;

  setUser: (token: string) => void;
  setTry: (value: boolean) => void;
  setUserId: (value: Number) => void;
  logout: () => void;
}

const useUserStore = create<UserStore>((set) => ({
	userToken: "",
	loginTryed: false,
	userId: -1,

	setUser: (token: string) => set(() => ({userToken: token})),
	setTry: (value: boolean) => set(() => ({loginTryed: value})),
	setUserId: (value: Number) => set(() => ({userId: value})),
  logout: () => set(() => ({ userToken: "", loginTryed: false, userId: -1 })),
}));

export default useUserStore;