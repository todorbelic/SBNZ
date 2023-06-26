export class RegisterRequest {
  username: string = '';
  firstName: string = '';
  lastName: string = '';
  password: string = '';

  public constructor(obj?: any) {
    if (obj) {
      this.firstName = obj.firstName;
      this.lastName = obj.lastName;
      this.username = obj.username;
      this.password = obj.password;
    }
  }
}
