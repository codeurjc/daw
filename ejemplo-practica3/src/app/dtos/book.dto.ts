import { ShopBasicDTO } from "./shopbasic.dto";

export interface BookDTO {
	id?: number,
	title: string,
	description: string,
	image: boolean,
	shops: ShopBasicDTO[]
}
