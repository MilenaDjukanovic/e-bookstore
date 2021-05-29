export interface IBaseAuthor{
  firstName: string;
  lastName: string;
  about: string;
}

export interface ICreateAuthor extends IBaseAuthor {

}

export interface IAuthor extends IBaseAuthor {
  id: number;
}

export class CreateAuthor implements ICreateAuthor {
  constructor(public firstName: string,
              public lastName: string,
              public about: string) {
  }
}

export class Author implements IBaseAuthor {
  constructor(public id: number,
              public firstName: string,
              public lastName: string,
              public about: string) {
  }
}
