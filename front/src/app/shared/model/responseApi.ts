export class ResponseApi {

  constructor(
    public isValid?: boolean | null,
    public message: string = ""
  ) {}
}
