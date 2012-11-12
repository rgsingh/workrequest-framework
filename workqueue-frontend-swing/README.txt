Explanation of Usage:

This module is stand-alone application with a Swing interface.
The required dependencies (workqueue-listener and, its derived dependency, workqueue-service) are
used. Launching com.rgsinfotech.workqueue.Server in the workqueue-service module
will only allow a single type of client as defined by the 
workqueue-frontend-remote module's com.rgsinfotech.workqueue.client.remote.Client class. 