# Movie management application

Movie management application in Clojure for educational purposes.

##Requirements

You first have to set up MySql database server and install Leiningen.

## Usage

In configuration/db.edn and configuration/init-db.edn you can change MySql connection parameters to match the ones on your machine.
In project.clj you can change the port on which the application is running. For running the application, run this command from terminal in your project location

````
$ lein ring server
````

##Examples
You can search, create, update and delete Movies, Actors and Roles.

## License

Copyright © 2022 FIXME

This program and the accompanying materials are made available under the
terms of the Eclipse Public License 2.0 which is available at
http://www.eclipse.org/legal/epl-2.0.

This Source Code may also be made available under the following Secondary
Licenses when the conditions for such availability set forth in the Eclipse
Public License, v. 2.0 are satisfied: GNU General Public License as published by
the Free Software Foundation, either version 2 of the License, or (at your
option) any later version, with the GNU Classpath Exception which is available
at https://www.gnu.org/software/classpath/license.html.
