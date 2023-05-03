export class BookWithAuthorsModel {
  id: number = 0;
  genre: number = 0;
  name: string = '';
  price: number = 0;
  authorFirstName: string = '';
  authorLastName: string = '';

  public constructor(obj?: any) {
    if (obj) {
      this.id = obj.id;
      this.genre = obj.genre;
      this.name = obj.name;
      this.price = obj.price;
      this.authorFirstName = obj.authorFirstName;
      this.authorLastName = obj.authorLastName;
    }
  }
}
