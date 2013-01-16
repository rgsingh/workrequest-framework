workrequest-framework
=====================

A simple event-based blueprint using a blocking queue. Demonstrates an event dispatching
mechanism sitting in front of another system such as a web service or an integration framework like Apache Camel.

workgen - will be the sample web app
workqueue-frontend-swing - a simple Swing app to inject data through the event bus
workqueue-frontend-remote - a remote proxy to a service which dispatches events over the event bus.

mvn clean install
