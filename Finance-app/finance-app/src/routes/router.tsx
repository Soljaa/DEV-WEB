import LoginPage from "../pages/LoginPage";
import ErrorPage from "../pages/ErrorPage";
import HomePage from "../pages/HomePage";
import ReportPage from "../pages/ReportPage";
import { createBrowserRouter } from "react-router-dom";
import PrivateRoutes from './PrivateRoutes';

const router = createBrowserRouter([
  {
    path: "/login",
    element: <LoginPage />,
    errorElement: <ErrorPage />,
  },
  {
    path: "/",
    element: <PrivateRoutes />,
    errorElement: <ErrorPage />,
    children: [
      { path: "", element: <HomePage /> },
      { path: "report", element: <ReportPage /> },
    ],
  },
]);

export default router;
