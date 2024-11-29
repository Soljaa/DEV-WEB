import { useNavigate } from "react-router-dom";
import { HomeIcon, BarChartIcon, TargetIcon, GearIcon, CaretLeftIcon, ExitIcon } from "@radix-ui/react-icons";
import { useState } from "react";

export default function SideBar() {
  const navigate = useNavigate();
  const [open, setOpen] = useState(true);
  const Menus = [
    { title: "Home", icon: <HomeIcon />, route: "/"},
    { title: "Histórico", icon: <BarChartIcon />, route: ""},
    { title: "Metas", icon: <TargetIcon />, route: ""},
    { title: "Configurações", icon: <GearIcon />, gap: true, route: ""},
    { title: "Logout", icon: <ExitIcon />, route: "/logout"},
  ];

  return (
    <div
      className={` ${
        open ? "w-72" : "w-20 "
      } bg-black h-screen p-5  pt-8 relative duration-300`}
    >
      <CaretLeftIcon
        className={`absolute cursor-pointer -right-3 top-9 w-7 border-dark-purple
       border-2 rounded-full size-10 bg-white  ${!open && "rotate-180"}`}
        onClick={() => setOpen(!open)}
      />
      <div className="flex gap-x-4 items-center">
        <h1
          className={`text-white origin-left font-medium text-xl duration-200 ${
            !open && "scale-0"
          }`}
        >
          FinanceHub
        </h1>
      </div>
      <ul className="pt-6">
        {Menus.map((option, index) => (
          <li
            key={index}
            className={`flex rounded-md p-2 cursor-pointer hover:bg-light-white text-gray-300 text-sm items-center gap-x-4 
          ${option.gap ? "mt-9" : "mt-2"} ${index === 0 && "bg-light-white"} `} onClick={() => navigate(option.route)}
          >
            {option.icon}
            <span className={`${!open && "hidden"} origin-left duration-200`}>
              {option.title}
            </span>
          </li>
        ))}
      </ul>
    </div>
  );
}
