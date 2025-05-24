from flask import Flask, request, jsonify
import requests
from requests.auth import HTTPBasicAuth

app = Flask(__name__)

# Configuration
JENKINS_BASE_URL = "https://b64a-105-155-143-70.ngrok-free.app:8080"  # Sans slash final
JENKINS_WEBHOOK_ENDPOINT = "/github-webhook/"
JENKINS_USER = "hassnae"
JENKINS_API_TOKEN = "11719a414b7f57a0867c7e672dc458a3d3"  # Token généré dans Jenkins > User > Configure > API Token

@app.route('/github-webhook-proxy', methods=['POST'])
def proxy_to_jenkins():
    try:
        # 1. Get CSRF Crumb from Jenkins
        crumb_url = f"{JENKINS_BASE_URL}/crumbIssuer/api/json"
        auth = HTTPBasicAuth(JENKINS_USER, JENKINS_API_TOKEN)
        
        crumb_response = requests.get(crumb_url, auth=auth)
        crumb_response.raise_for_status()  # Lève une exception pour les codes 4XX/5XX
        
        crumb_data = crumb_response.json()
        crumb = crumb_data['crumb']
        crumb_header = crumb_data['crumbRequestField']  # Normalement "Jenkins-Crumb"

        # 2. Forward request to Jenkins with proper headers
        jenkins_url = f"{JENKINS_BASE_URL}{JENKINS_WEBHOOK_ENDPOINT}"
        headers = {
            crumb_header: crumb,
            "Content-Type": "application/json"
        }

        jenkins_response = requests.post(
            jenkins_url,
            headers=headers,
            json=request.json,
            auth=auth
        )
        jenkins_response.raise_for_status()

        return jsonify({
            "status": "success",
            "jenkins_response": jenkins_response.json() if jenkins_response.content else None
        }), jenkins_response.status_code

    except requests.exceptions.RequestException as e:
        return jsonify({
            "status": "error",
            "message": str(e),
            "details": f"Failed to process webhook: {e.response.text}" if hasattr(e, 'response') else None
        }), 500

if __name__ == '__main__':
    app.run(host='0.0.0.0', port=5000, debug=True)