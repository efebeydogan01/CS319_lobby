export class User {
  constructor(public id: number, private token: string, private tokenExpDate: Date) {
  }

  getToken() {
    if ( !this.tokenExpDate || new Date() > this.tokenExpDate) {
      return null;
    }
    return this.token;
  }
}
