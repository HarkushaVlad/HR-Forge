import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'fieldNameFormat',
})
export class FieldNameFormatPipe implements PipeTransform {
  transform(value: string): string {
    if (!value) return value;
    const words = value.replace(/([a-z])([A-Z])/g, '$1 $2');
    return words
      .split(' ')
      .map((word) => word.charAt(0).toUpperCase() + word.slice(1))
      .join(' ');
  }
}
