# HomeAwayTestApp
<h3>Application allowing users to find places in Seattle, Washington, by the keywords, browser search result at list and google map, view details of each place.</h3>
The app contains 3 screens:
<ul>
<li><b>Search screen: </b><br/>The screen with EditText where user type search keywords (e.g. Pizza, Tea, Caffee) and application perform dynamic API-request and display result typeahead.Each result item is clickable and displays
<ul>
<li>Venue image</li>
<li>Name</li>
<li>Category</li>
<li>Distance from te center of Seattle</li>
</ul>
Click on the item redirects user to the detail screen of the venue.
</li>
<li><b>Details screen:</b><br/>This screen consists of static map with markers on Seattle center and the venue's location, name, category, rating, contacts, photos etc.</li>
<li><b>Map search screen:</b><br/>User can navigate to this one clicking on the Floating action button at the search screen. The screen iteself contains MapView where all search results are displayed.</li>
</ul>

<h3>Architecture</h3>
<p>The project implements MVVM architecture pattern. Also the Repository-model is used (ViewModel queries data through a repository that contains links to data sources, i.e. API-interfaces)
So, the structure is like following:<br/>
API <-> Repository <-> ViewModel <-> View
</p>
<h3>Libraries and tools</h3>
<ul>
<li>Kotlin Coroutines (structured concurrency)</li>
<li>Navigation components (navigation between app's screens)</li>
<li>Dagger Hilt (dependency injection)</li>
<li>Google Maps API (google maps usage)</li>
<li>Retrofit (interaction with REST Api)</li>
<li>Foursquare API (Places API)</li>
<li>Glide (images loading)</li>
</ul>

<br/><br/>

<h3>Instruction for building an app</h3>
<h4>For developers:</h4>
<p>If you're developer, you could perform simple operation of pulling the project from GitHub using the repository link</p>
<h4>For users:</h4>
<p>The APK file is deployed <a href="https://drive.google.com/file/d/1vXGQxPsLflrDtKfQw5wMBPZCaJ6KHOoa/view?usp=sharing">in the Google Drive</a> and you can dounload it and install.</p>
