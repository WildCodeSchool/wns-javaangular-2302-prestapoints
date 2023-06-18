export class Alert {
    
    constructor(
        public type?: string | null,
        public message?: string | null,
        public timer?: boolean | null,
        public duration?: number | null,
        ) {
    }
}
