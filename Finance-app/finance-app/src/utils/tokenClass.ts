class TokenClass {
  static token = {} as TokenClass;

  private userToken: string;

  private constructor(userToken: string) {
    this.userToken = userToken;
  }

  public static createToken = (token: string): void => {
    TokenClass.token = new TokenClass(token);
     // setcookie
  };
  public static getToken = () => {
    return TokenClass.token.userToken;
  };
  public static resetToken = () => {
    TokenClass.token.userToken = "";
    // delete cookie
  };
}

export default TokenClass;
