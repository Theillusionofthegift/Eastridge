const express = require('express')
const app = express()
const port = 8080

app.get('/', (req, res) => {
  res.header('statusCode', 200);
  res.send(`
            <!doctype html>
            <html>
            <body>
                <main>
                      Hello World!
                </main>
            </body>
            </html>`);
 })

app.listen(port, () => {
  console.log(`listening at http://localhost:${port}`)
})