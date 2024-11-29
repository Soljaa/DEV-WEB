import { Navigate, useLocation } from "react-router-dom";
import Layout from "./Layout";
import useUserStore from "../store/userStore";

const PrivateRoutes = () => {
  const userToken = useUserStore((s) => s.userToken);
  const location = useLocation();
  console.log(userToken)
  if (!userToken) {
    return <Navigate to="/login" state={{ from: location.pathname }} />;
  }
  return <Layout />;
};
export default PrivateRoutes;
