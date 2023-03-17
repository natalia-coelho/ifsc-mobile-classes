# Mobile Classes at IFSC

### Settings

- Pixel 2 API 30.

### Notes
### Fragments

- **Modularity**
- Generally, each screen in your Android app is associated with one or more *[fragments](https://developer.android.com/guide/components/fragments)*

<aside>
� A `[Fragment](https://developer.android.com/reference/androidx/fragment/app/Fragment)`represents a reusable portion of your app's UI. A fragment defines and manages its own layout, has its own lifecycle, and can handle its own input events. Fragments cannot live on their own--they must be *hosted*
 by an activity or another fragment. The fragment’s view hierarchy becomes part of, or *attaches to* the host’s view hierarchy.

</aside>

**Setting Strings in Resources**

Having the strings in a resource file has several advantages. You can change the value of string without having to change any other code. This simplifies translating your app to other languages, because your translators don't have to know anything about the app code.

example: 

`<string name="hello_first_fragment">Hello World!</string>`

`hello_first_fragment` will point to the value given instead of passing it statically.
