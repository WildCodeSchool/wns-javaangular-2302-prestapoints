export class User {
    public id?: string;
    public roles?: string;
    public creationDate?: string;

    constructor(
        public firstname?: string | null,
        public lastname?: string | null,
        public email?: string | null,
        public password?: string | null,
        public phone?: string | null,
        public image?: File | null
        ) {
    }
}
