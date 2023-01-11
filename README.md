# Ring app

A Clojure library designed to ... well, that part is up to you.

## Dependencies

Muuntaja Clojure library for fast http format negotiation, encoding and decoding. More Information [Here](https://cljdoc.org/d/metosin/muuntaja/0.6.8/doc/readme)
encoder/decoder API

## Usage

JSON encoded request and response.

```sh
$ curl -H "Content-Type: application/json" -X POST -d '{"id":1}' http://localhost:3000
```

EDN encoded request and response.

```sh
$ curl -H "Content-Type: application/edn" -H "Accept: application/edn"  -X POST -d '{"id":1}' http://localhost:3000
```

## License

Copyright © 2023 FIXME

This program and the accompanying materials are made available under the
terms of the Eclipse Public License 2.0 which is available at
http://www.eclipse.org/legal/epl-2.0.

This Source Code may also be made available under the following Secondary
Licenses when the conditions for such availability set forth in the Eclipse
Public License, v. 2.0 are satisfied: GNU General Public License as published by
the Free Software Foundation, either version 2 of the License, or (at your
option) any later version, with the GNU Classpath Exception which is available
at https://www.gnu.org/software/classpath/license.html.
