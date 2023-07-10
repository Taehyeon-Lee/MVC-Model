# MVC - Photo Album
The Photobook is an application that may be used to generate a photo album based on the user's instructions. The file parser reads the instructions from the text file and creates the model using an adapter class. The newly created model returns to the controller a list of Snapshot objects, which is then passed on to the View. The album is then rendered using snapshot data by the view. The view might be either graphic or web-based.