import axios from "axios";

const CLOUDINARY_UPLOAD_URL =
  "https://api.cloudinary.com/v1_1/dyjeugvrl/upload";
const CLOUDINARY_UPLOAD_PRESET = "sof308_vuejs";

export const uploadImage = async (file) => {
  const cloudinaryFormData = new FormData();
  cloudinaryFormData.append("file", file);
  cloudinaryFormData.append("upload_preset", CLOUDINARY_UPLOAD_PRESET);

  try {
    console.log("Uploading file to Cloudinary...");
    const uploadRes = await axios.post(
      CLOUDINARY_UPLOAD_URL,
      cloudinaryFormData,
      { withCredentials: false }
    );

    const finalAvatarUrl = uploadRes.data.secure_url;
    console.log("Upload thành công. URL:", finalAvatarUrl);
    return finalAvatarUrl;
  } catch (error) {
    console.error("Lỗi upload Cloudinary:", error);
    return;
  }
};
