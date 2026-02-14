export default function Home() {

  const elems = [
    { id: 1, desc: 'Elem1', visible: true },
    { id: 2, desc: 'Elem2', visible: true },
    { id: 3, desc: 'Elem3', visible: false }
  ]

  return (
    <ul>
      {elems.map(elem => 
        elem.visible && <li key={elem.id}>{elem.desc}</li>
      )}
    </ul>
  );
}
