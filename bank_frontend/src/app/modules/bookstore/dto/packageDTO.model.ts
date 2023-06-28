export class PackageDTOModel {
  id: string = '';
  type: number = 0;

  public constructor(obj?: any) {
    if (obj) {
      this.id = obj.id;
      this.type = obj.type;
    }
  }
}


