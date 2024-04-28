import instaloader

def download():
    url = input("Digite a URL: ")
    nome = input("Digite o nome do arquivo: ")
    L = instaloader.Instaloader()
    shortcode = url.split("/")[-2]
    reel_media = instaloader.Post.from_shortcode(L.context, shortcode)
    L.download_post(reel_media, target=f"{nome}")

download()
