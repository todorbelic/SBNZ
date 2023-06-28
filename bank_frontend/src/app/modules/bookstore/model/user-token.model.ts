export class UserToken {
  sub: string = '';
  id: string = '';
  role: string = '';

  constructor(sub: string, id: string, role: string ) {
    this.sub = sub;
    this.id = id;
    this.role = role;
  }
}
