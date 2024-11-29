import { Outlet } from "react-router-dom";
import SideBar from "../components/sideBar";

function Layout() {
  return (
    <div className="flex">
      <SideBar />
      <div className="flex-grow container mt-3">
        <Outlet />
      </div>
    </div>
  );
}

export default Layout;