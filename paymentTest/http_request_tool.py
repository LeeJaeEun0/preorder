import requests
from concurrent.futures import ThreadPoolExecutor

def send_http_request(base_url, request_number, preorder_product_id):
    try:
        # 'userId'와 'preorderProductId'를 경로 변수로 포함한 URL 생성
        url = f"{base_url}/{request_number}/{preorder_product_id}"
        # 주어진 URL로 HTTP POST 요청을 보냄
        response = requests.post(url)
        # 요청이 성공적으로 완료되면 상태 코드와 함께 메시지를 출력
        print(f"POST request to {url} completed with status code {response.status_code}")
    except Exception as e:
        # 요청 중에 오류가 발생하면 오류 메시지를 출력
        print(f"Error sending POST request to {url}: {e}")

def main():
    num_requests = 10000  # 동시에 보낼 요청의 수 설정
    base_url = "http://localhost:8084/api/preorderProducts"  # 기본 URL 설정
    preorder_product_id = 1  # preorderProductId 설정

    with ThreadPoolExecutor(max_workers=100) as executor:
        # 각 요청에 대해 'userId'를 경로 변수로 포함하여 POST 요청을 보냄
        tasks = [executor.submit(send_http_request, base_url, i, preorder_product_id) for i in range(1, num_requests + 1)]

        # 모든 태스크가 완료될 때까지 대기
        for future in tasks:
            future.result()

if __name__ == "__main__":
    main()