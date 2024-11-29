import { useEffect } from "react";
import { Navigate, useLocation } from "react-router-dom";
import useUserStore from "../store/userStore";

export default function Logout() {
  const logout = useUserStore((s) => s.logout);
  const location = useLocation();

  useEffect(() => {
    logout();
  }, [logout]);

  return <Navigate to="/login" state={{ from: location.pathname }} />;
}
