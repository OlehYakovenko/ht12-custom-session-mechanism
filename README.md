**Build a custom session mechanism**

* **Create** a new servlet app
  * Process a GET request to /hello
  * Support optional name parameter (e.g. /hello?name=Viktor )
  * Respond with "Hello!" or "Hello, {name}!" if the name is provided
  * Leverage Servlet Session to store the name (so the client gets personalised hello even when the name is not provided)
* **Build** a custom session mechanism
  * Come up with some identifier (session id)
  * Create some session class
  * Create some map that stores sessions by session id
  * Build an API that allows to get/create a session based on the request
