import {
  Card,
  CardContent,
  CardDescription,
  CardHeader,
  CardTitle,
} from "./ui/card";
import { cn } from "../lib/utils";
import { Button } from "./ui/button";
import { z } from "zod";
import { useForm } from "react-hook-form";
import { zodResolver } from "@hookform/resolvers/zod";
import useAPIAuth from "../hooks/useAPIAuth";
import { useEffect } from "react";
import useUserStore from "../store/userStore";
import { useLocation, useNavigate } from "react-router-dom";
import TokenClass from "../utils/tokenClass";
import User from "../interfaces/user";
import TokenResponse from "../interfaces/tokenResponse";

const schema = z.object({
  username: z.string().min(1, { message: "O usuário deve ser informado." }),
  password: z.string().min(1, { message: "A senha deve ser informada." }),
});

type FormLogin = z.infer<typeof schema>;

export default function LoginForm() {
  const setUser = useUserStore((s) => s.setUser);
  const setTry = useUserStore((s) => s.setTry);
  const setUserId = useUserStore((s) => s.setUserId);
  const loginTryed = useUserStore((s) => s.loginTryed);

  const location = useLocation();
  const navigate = useNavigate();

  const {
    register,
    handleSubmit,
    setFocus,
    formState: { errors },
  } = useForm<FormLogin>({
    resolver: zodResolver(schema),
  });

  useEffect(() => {
    setFocus("username");
    setTry(false);
    setUser("");
    TokenClass.resetToken();
  }, []);

  const onSubmit = async ({ username, password }: FormLogin) => {
    const user: User = { username, password };
    const { login } = useAPIAuth();

    try {
      const response = await login(user);
      const tokenResponse: TokenResponse = { token: response.token, user_id: response.user_id };
      TokenClass.createToken(tokenResponse.token);
      setUser(tokenResponse.token);
      setUserId(tokenResponse.user_id);

      if (location.state && location.state.from) {
        navigate(location.state.from);
      } else {
        navigate("/home");
      }

    } catch (error) {
      setUser("");
      setTry(true);
    }
  };

  return (
    <div className="flex items-center justify-center min-h-screen">
      <Card className={cn("w-[380px]", "h-[380px]")}>
        <CardHeader>
          <CardTitle className="text-2xl text-center font-bold">
            FinanceHub
          </CardTitle>
          <CardDescription className="text-center">
            Tenha todas as suas finanças no mesmo lugar!
          </CardDescription>
        </CardHeader>
        <CardContent className="grid gap-3">
          <form onSubmit={handleSubmit(onSubmit)} className="grid gap-3">
            <div>
              <label
                htmlFor="username"
                className="block text-sm font-medium text-gray-700"
              >
                Usuário
              </label>
              <input
                type="text"
                id="username"
                {...register("username")}
                className="mt-1 block w-full border shadow-sm rounded-md border-black-600 focus:border-indigo-500 focus:ring-indigo-500"
              />
              {errors.username && (
                <span className="text-red-500 text-sm">
                  {errors.username?.message}
                </span>
              )}
            </div>
            <div>
              <label
                htmlFor="password"
                className="block text-sm font-medium text-gray-700"
              >
                Senha
              </label>
              <input
                type="password"
                id="password"
                {...register("password")}
                className="mt-1 block w-full border shadow-sm rounded-md border-black-300 focus:border-indigo-500 focus:ring-indigo-500"
              />
              {errors.password && (
                <span className="text-red-500 text-sm">
                  {errors.password?.message}
                </span>
              )}
            </div>
            {loginTryed && (
              <div className="bg-red-100 border border-red-400 text-red-700 px-4 py-3 rounded relative" role="alert">
              <strong className="font-bold">Login incorreto! </strong>
              <span className="block sm:inline">Verifique o username e a senha.</span>
            </div>
            )}
            <Button type="submit" className="w-full">
              Login
            </Button>
          </form>
        </CardContent>
      </Card>
    </div>
  );
}
