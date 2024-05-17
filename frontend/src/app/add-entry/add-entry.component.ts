import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-add-entry',
  templateUrl: './add-entry.component.html',
  styleUrls: ['./add-entry.component.css']
})
export class AddEntryComponent {
  entryForm: FormGroup;

  constructor(private fb: FormBuilder) {
    this.entryForm = this.fb.group({
      date: ['', Validators.required],
      meal: ['', Validators.required],
      calories: ['', [Validators.required, Validators.min(0)]],
      notes: ['']
    });
  }

  onSubmit(): void {
    if (this.entryForm.valid) {
      const newEntry = this.entryForm.value;
      let entries = localStorage.getItem('calorieEntries');
      let entriesArray = entries ? JSON.parse(entries) : [];
      entriesArray.push(newEntry);
      localStorage.setItem('calorieEntries', JSON.stringify(entriesArray));
      this.entryForm.reset();
      alert('Eintrag erfolgreich hinzugefügt!');
    }
  }
}
