export class Prestation {
  constructor(
    public category: string,
    public title: string,
    public image: string,
    public addPoint: number,
    public duration: string //public city: string
  ) {}
}

const prestation = new Prestation(
  'category',
  'title',
  'image',
  50,
  'duration'
  //'city'
);
