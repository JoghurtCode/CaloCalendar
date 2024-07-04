import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-fridge',
  templateUrl: './fridge.component.html',
  styleUrls: ['./fridge.component.css']
})
export class FridgeComponent implements OnInit {
  recipes: any[] = [
    {
      id: 1,
      name: 'Omelett mit Käse',
      ingredients: ['Milch', 'Eier', 'Käse'],
      details: 'Ein einfaches Omelett mit Käse.\nSchritt 1: Eier und Milch verquirlen.\nSchritt 2: Die Mischung in einer Pfanne erhitzen.\nSchritt 3: Käse hinzufügen und schmelzen lassen.\nSchritt 4: Das Omelett falten und servieren.'
    },
    {
      id: 2,
      name: 'Gemüsepfanne',
      ingredients: ['Gemüse', 'Eier', 'Milch'],
      details: 'Eine gesunde Gemüsepfanne.\nSchritt 1: Gemüse schneiden und in einer Pfanne anbraten.\nSchritt 2: Eier und Milch verquirlen und zum Gemüse hinzufügen.\nSchritt 3: Alles gut durchrühren und servieren.'
    },
    {
      id: 3,
      name: 'Käse-Gemüse-Auflauf',
      ingredients: ['Käse', 'Milch', 'Gemüse'],
      details: 'Ein leckerer Käse-Gemüse-Auflauf.\nSchritt 1: Gemüse schneiden und in eine Auflaufform geben.\nSchritt 2: Milch und geriebenen Käse darüber gießen.\nSchritt 3: Im Ofen bei 180°C backen, bis der Käse goldbraun ist.'
    }
  ];
  currentRecipe: any = { name: '', ingredients: [], details: '' };

  constructor(private snackBar: MatSnackBar) {}

  ngOnInit(): void {
    this.loadRecipes();
    this.shuffleRecipe();
  }

  loadRecipes(): void {
    const storedRecipes = JSON.parse(localStorage.getItem('fridgeRecipes') || '[]');
    if (storedRecipes.length > 0) {
      this.recipes = storedRecipes;
    } else {
      localStorage.setItem('fridgeRecipes', JSON.stringify(this.recipes));
    }
  }

  shuffleRecipe(): void {
    const randomIndex = Math.floor(Math.random() * this.recipes.length);
    this.currentRecipe = this.recipes[randomIndex];
  }

  addToFavorites(): void {
    let favorites = JSON.parse(localStorage.getItem('favoriteRecipes') || '[]');
    favorites.push(this.currentRecipe);
    localStorage.setItem('favoriteRecipes', JSON.stringify(favorites));
    this.snackBar.open('Rezept zu Favoriten hinzugefügt', 'Schließen', {
      duration: 2000,
    });
  }
}
