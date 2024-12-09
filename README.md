# Atlántida Digital Bank

## Customer Domain Data Access Layer
### Responsibility
Manages the persistence and retrieval of system data. It is responsible for interacting with the database, keeping the data access logic separate from the business logic.

#### gRPC (internal)
For to establish communication between internal layers using the gRPC protocol, proto files act as contracts that define the structure and rules for communication between services.

#### Proto file example

```protobuf
syntax = "proto3"; // Protocol Buffers version

package example; // Namespace

// Message definition
message Request {
  string name = 1;
}

message Response {
  string message = 1;
}

// Service definition
service ExampleService {
  rpc SayHello (Request) returns (Response);
}


```

#### Best Practices for Managing `.proto` Files in gRPC

#### 1. Mapping Elements in the `.proto` File
The `.proto` file should define all the necessary elements for efficient communication between microservices, as it acts as a contract between the client and the server.

#### 2. Impact of Changes in Contracts
Any modification to a `.proto` file affects all the services consuming it, as these services generate their code based on the contract. Therefore, any change in the contract may lead to incompatibilities if not properly managed.

#### 3. Creating New Contracts for Modifications
It is a good practice not to modify an existing contract directly to avoid compatibility issues. Instead, new versions of the contracts should be created, while keeping the old ones intact to ensure that existing clients continue to function without disruptions.

#### 4. Thorough Analysis Before Defining the Contract
Carefully reviewing `.proto` files before implementing them is crucial, as they form the foundation of communication between services. This minimizes the need for changes later.

#### 5. Versioning Strategy
Implementing a versioning system for contracts (e.g., `service ExampleServiceV1` and `service ExampleServiceV2`) helps manage changes in an organized manner and allows clients to upgrade progressively.

By following these practices, you can ensure stable and efficient communication between services while minimizing risks associated with contract changes.

#### Kafka (external)

This layer is not responsible for confirming transactions through events. However, if required solely for logging purposes, these logs should be strictly limited to CRUD events. For instance, confirming a successful query.