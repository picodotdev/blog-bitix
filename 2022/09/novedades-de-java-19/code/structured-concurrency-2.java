void serve(ServerSocket serverSocket) throws IOException, InterruptedException {
    try (var scope = new StructuredTaskScope<Void>()) {
        try {
            while (true) {
                var socket = serverSocket.accept();
                scope.fork(() -> handle(socket));

            }
        } finally {
            // If there's been an error or we're interrupted, we stop accepting
            scope.shutdown();  // Close all active connections
            scope.join();
        }
    }
}