const http = require('http');
const fs =  require('fs');

const requestListener = function (req, res) {
    if(req.url === '/users')
    {
        fs.readFile('users.html', function(err, data) {
            if(err) {
                throw err;
            }
            res.writeHead(200, {'Content-Type': 'text/html'});
            res.write(data);
            return res.end();
          });
    }
    else if (req.method === 'POST')
    {
        let buffer = '';
        req.on('data', chunk => {
             buffer += chunk;
          });
        req.on('end', () => {
            // Define to JSON type
           // let jsonContent = JSON.parse(buffer);
           let jsonContent = JSON.string(buffer);
            res.end(`
            <!doctype html>
            <html>
            <body>
                <main>
                    ${jsonContent}
                </main>
            </body>
            </html>`);
          });
    }
    else
    {
        //res.end("This is the home page");
        res.end(`
        <!doctype html>
        <html>
        <body>
            <form action="/" method="POST" enctype='text/plain'>
                <label for="myfile">Select a file:</label>
                <input type="file" id="json" name="json">  <br />
                <button>Save</button>
            </form>
        </body>
        </html>`);
    }
    
   
}
const server = http.createServer(requestListener);
server.listen(8080);