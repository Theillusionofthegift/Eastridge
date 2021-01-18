const http = require('http');
const fs =  require('fs');

const requestListener = function (req, res) {
    if(req.url === '/users')
    {
        //read the users.html file returning a list of users
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
        //read data into buffer from request object
        let buffer = '';
        req.on('data', chunk => {
             buffer += chunk;
          });
        //return html with buffer in the main 
        req.on('end', () => {  
           res.writeHead(200, {
            'Content-Type': 'text/html'});
            res.end(`
            <!doctype html>
            <html>
            <body>
                <main>
                ${buffer}
                </main>
            </body>
            </html>`);
           })
    }
    else
    {
        //HTML form that sends a POST request to the server
        res.end(`
        <!doctype html>
        <html>
        <body>
            <form action="/" method="POST" target="_blank">
                <input type="file" id="json" name="json">  <br />
                <input type="submit" value="Submit">
            </form>
        </body>
        </html>`);
    }
    
   
}
const server = http.createServer(requestListener);
server.listen(8080);