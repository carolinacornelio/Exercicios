import instaloader

L = instaloader.Instaloader()
user = input("Digite o nome do perfil: ")

try:
    profile = instaloader.Profile.from_username(L.context, user)
except instaloader.exceptions.ProfileNotExistsException:
    print("Perfil não encontrado")
    exit()
    
for post in profile.get_posts():
    L.download_post(post, target=profile.username) 