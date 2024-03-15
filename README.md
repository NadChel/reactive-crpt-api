This project is a proposed solution to a test task accompanying a specific job opening

It intentionally diverges from the specification in a few ways. Most notably, I decided against cramming the entire project into one `java` file, especially since extensibility is one of the goals defined in the spec

This projects hints at a few ways it can be extended. For example, the `BucketResolver` type resolves a bucket of tokens from a passed-in exchange which provides an opportunity to have multiple rate limiting strategies, e.g. ones based on API keys contained in request headers. The only OOB implementation of the `BucketResolver` interface, however, adopts a simpler approach with one bucket for all exchanges

Rate limiting is configured via the `application.yml` property file. For example, the following configuration would set the rate limit to 1 rps (60 requests per one minute)
```yml
crpt:
  rate-limiter:
    capacity: 60
    duration: 1m
```
The application's only endpoint is `POST /api/v3/lk/documents/create` that accepts a JSON of a `DocumentRequestDto` and simulates persistence of a document specifying a list of products. Here's an example of a valid cURL (quotes are escaped for Command Prompt compatibility):
```shell
curl -X POST http://localhost:8080/api/v3/lk/documents/create -H "Content-Type: application/json" -d "{\"description\": {\"participantInn\": \"string\"}, \"doc_id\": \"string\", \"doc_status\": \"string\", \"doc_type\": \"LP_INTRODUCE_GOODS\", \"importRequest\": true, \"owner_inn\": \"string\", \"participant_inn\": \"string\", \"producer_inn\": \"string\", \"production_date\": \"2020-01-23\", \"production_type\": \"string\", \"products\": [{\"certificate_document\": \"string\", \"certificate_document_date\": \"2020-01-23\", \"certificate_document_number\": \"string\", \"owner_inn\": \"string\", \"producer_inn\": \"string\", \"production_date\": \"2020-01-23\", \"tnved_code\": \"string\", \"uit_code\": \"string\", \"uitu_code\": \"string\"}], \"reg_date\": \"2020-01-23\", \"reg_number\": \"string\"}"
```
The default port is not overridden and stays at `8080`
