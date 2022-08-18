import { Pipe, PipeTransform } from "@angular/core";

@Pipe({
    name: "number",
})
export class NumberPipe implements PipeTransform {
    transform(value: number, ...args: unknown[]): string {
        return value.toLocaleString();
    }
}
