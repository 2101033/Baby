/**
 * 共有API利用スクリプト
 */

const shareData = {
  title: "招待コード",
  text: "aiueo123",
};

 async function shareInvCode() {
	 try {
    await navigator.share(shareData);
  } catch (err) {
    console.log(err);
  }
 }