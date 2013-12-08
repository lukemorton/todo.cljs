# React Todo app in ClojureScript

So I'm learning [Clojure][1] and [ClojureScript][2], I heard
about [React][3] and decided to put the two together. There's
a bit of hype about React in the ClojureScript community at
the moment, it really looks nice.

To run this example you will first need to install [lein][4]
and then from the project root:

``` sh
lein cljsbuild once todo
```

To develop run:

``` sh
lein cljsbuild auto todo
```

[1]: http://clojure.org/
[2]: https://github.com/clojure/clojurescript
[3]: http://facebook.github.io/react/
[4]: https://github.com/technomancy/leiningen
