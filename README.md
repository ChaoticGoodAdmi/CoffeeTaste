# CoffeeTaste

A simple Spring Boot backend for recording and searching coffee tasting experiences. The application exposes a RESTful API and persists data in a MongoDB database.

## Functional Features

- **Add a coffee tasting experience**: Create a record of a coffee you tried, including whether it was a drip‑bag or roasted beans (`coffeeType`), the roaster company, origin country with continent, processing method (washed, anaerobic, etc.), lot name, taste profile, aftertaste description, and a rating from 0‑10.
- **List and search experiences**: Retrieve all stored experiences or filter by roaster company, continent, country or rating range. You can also search for terms contained in the taste profile, aftertaste or other textual fields.
- **Get a single experience**: Fetch a specific experience by its ID.

## Non‑Functional Features

- Built with **Kotlin** and **Spring Boot**.
- Uses **MongoDB** (NoSQL) as its persistent storage.
- Clean, human‑readable code organized into model, repository, service and controller layers.
- Contains database and application configuration scripts to help run the app locally.

## Running the Application Locally

### Prerequisites

- **Java 17+** installed on your machine.
- **Gradle** (the project includes the Gradle wrapper).
- **Docker** and **Docker Compose** for running the MongoDB container.

### Steps

1. **Clone the repository** and check out the `coffee-taste-backend` branch (or merge it into master once the PR is accepted):

   ```bash
   git clone https://github.com/ChaoticGoodAdmi/CoffeeTaste.git
   cd CoffeeTaste/CoffeeTaste
   ```

2. **Start MongoDB** using the provided `docker-compose.yml` file:

   ```bash
   cd src/scripts
   docker compose up -d
   ```

   This will start a `mongo` container on port `27017` with a persistent volume.

3. **Run the Spring Boot application** using the Gradle wrapper:

   ```bash
   cd ..
   ./gradlew bootRun
   ```

   The application will start on port **8080** (see `src/main/resources/application.yml`). It will connect to the local MongoDB instance defined in the `application.yml` file.

4. To stop the database container when you’re done:

   ```bash
   cd src/scripts
   docker compose down
   ```

## REST API

All endpoints are prefixed with `/api/experiences`.

### Create a new experience

```
POST /api/experiences
Content-Type: application/json
```

**Request body**:

```json
{
  "coffeeType": "DRIP_BAG",
  "roasterCompany": "Acme Roasters",
  "originCountry": "Ethiopia",
  "continent": "Africa",
  "processingMethod": "Washed",
  "lotName": "Yirgacheffe Lot 1",
  "tasteProfile": "Floral, citrus and tea-like",
  "aftertaste": "Sweet with jasmine notes",
  "rating": 9
}
```

- `coffeeType` must be either `"DRIP_BAG"` or `"ROASTED_BEANS"`.
- `rating` must be an integer between 0 and 10.

Returns the saved experience with its generated `id`.

### List experiences with optional filters

```
GET /api/experiences
```

Query parameters (all optional):

| Parameter        | Description                                                        |
|------------------|--------------------------------------------------------------------|
| `roasterCompany` | Only include experiences from this roaster company                 |
| `continent`      | Only include experiences from this continent                       |
| `country`        | Only include experiences from this country                         |
| `minRating`      | Minimum rating (inclusive)                                         |
| `maxRating`      | Maximum rating (inclusive)                                         |
| `search`         | Search term applied to taste profile, aftertaste, lot or origin    |
| `page`           | Page number for paginated results (default 0)                      |
| `size`           | Page size (default 10)                                             |

The response is a list of experience objects matching the filters.

### Get a single experience

```
GET /api/experiences/{id}
```

Returns the experience with the specified `id`, or `404 Not Found` if it doesn’t exist.

## Database Scripts

The `src/scripts/docker-compose.yml` file provides a quick way to run MongoDB locally for development. The application uses the `coffeetaste` database and connects to `localhost:27017` as specified in `application.yml`.

Feel free to enhance the application by adding more fields or endpoints as needed.
